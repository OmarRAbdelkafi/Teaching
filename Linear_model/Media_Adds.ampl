###################  Groups   ######################
set Channels;

###################  Param    ######################
param budget >= 0;
param cost {Channels} >= 0;
param public {Channels} >= 0;
param min_unit {Channels} >= 0;
param Global_availabilities >= 0;
param Available_day {Channels} >=0;

###################  Variables  ######################
var nbr_unit {c in Channels} >= 0;

#Pre computing
var Budget_used = sum {c in Channels} (cost[c] * nbr_unit[c]);
var NRJ_used = sum {c in Channels} Available_day[c] * nbr_unit[c];

###################  Objectif     ######################
maximize target :
	sum {c in Channels} nbr_unit[c] * public[c];

###################  Constraint   ######################
subject to lim_budget :
	Budget_used <= budget;
subject to engagement {c in Channels}  :
	nbr_unit[c] >= min_unit[c];
subject to NRJ :
	NRJ_used <= Global_availabilities;

###################  Data       ########################

data;

set Channels := TV Newspaper Radio;

param budget := 1000000;
param Global_availabilities := 500;

param:     cost   public   min_unit Available_day :=
TV         20000  1800000 		10        5
Newspaper  10000  1000000 		0        	15
Radio      2000 	250000  		120	    	1;

/*
reset;
option solver "./minos";
model Media_Adds.ampl;
*/

solve;
display nbr_unit;
display Budget_used;
display budget;
display NRJ_used;
display Global_availabilities;

###################  Marginal Var  #######################
##### Null if the constraint are not fully exploited #####
##########################################################
display lim_budget;
display NRJ;
