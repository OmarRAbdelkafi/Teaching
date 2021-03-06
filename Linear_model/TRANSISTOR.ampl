set TRANS;

param hfe{TRANS};
param vbe{TRANS};

param MAXHFE;
param MAXVBE;
param E;

# selected[t] = 1 if the transistor is selected
var selected{TRANS} binary;

var minhfe;
var maxhfe;
var minvbe;
var maxvbe;
var dispersion;

subject to h1{t in TRANS}:
	minhfe <= selected[t] * hfe[t] + (1-selected[t]) * MAXHFE;

subject to h2{t in TRANS}:
	maxhfe >= selected[t]*hfe[t];

subject to v1{t in TRANS}:
	minvbe <= selected[t]*vbe[t] + (1-selected[t]) * MAXVBE;

subject to v2{t in TRANS}:
	maxvbe >= selected[t]*vbe[t];

subject to cont_nombre_exclus:
   (sum {t in TRANS} selected[t]) >= (card (TRANS) - E);

subject to cont_disp_h:
	dispersion >= (maxhfe-minhfe)/MAXHFE;

subject to cont_disp_v:
	dispersion >= (maxvbe-minvbe)/MAXVBE;

minimize dispersion_min :
	dispersion;

data;

param MAXHFE := 600;
param MAXVBE := 1.0;
param E := 4;

param : TRANS : hfe vbe =
T1 369.713399 0.505002
T2 172.445927 0.527908
T3 388.349744 0.678436
T4 345.246256 0.506357
T5 159.318987 0.601071
T6 330.622531 0.643204
T7 160.220311 0.617853
T8 429.403684 0.531932
T9 220.125258 0.531096
T10 101.105138 0.575985
T11 473.747183 0.620745
T12 414.865893 0.607246
T13 89.400099 0.558636
T14 338.676073 0.640914
T15 380.631659 0.654614
T16 477.658861 0.673297
T17 188.986802 0.627137
T18 131.327049 0.571054
T19 400.910157 0.636710
T20 354.565503 0.534228
T21 131.701247 0.575891
T22 392.307125 0.668570
T23 164.524036 0.506420
T24 250.582394 0.513238
T25 156.313272 0.599846
T26 121.435795 0.527926
T27 423.506906 0.585687
T28 230.998222 0.699465
T29 304.763147 0.518182
T30 480.551455 0.530568
T31 446.039682 0.584432
T32 242.383656 0.619178;
