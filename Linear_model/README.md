# Linear Model
In this cours we are using the AMPL software which use diffrent solver like minos and gurobi, each solver contain different exact algorithm like simlex, branch and cut...

In this repository we have two example :

1. Media_adds.ampl
An advertising campny wants to promote a new product with a budget of one million euros. You can advertise in different channels: TV, newspaper or radio. We are looking for ways to use the budget to maximize the number of potential consumers. Each channel has different costs and affects a different number of consomers. In order to have creative advertisements, a constraint is created about the time spent to prodoce new each week advertisements.

2. TRANSISTOR.ampl
We have a set of transistor in our example (31 transistors), the objective is to minimize the dispersion of this set. For this we can exclude up to 4 transistors from the set. The Model must make it possible to find how many and which transistors can we exclude to get the best possible solution.

The dispersion is computed with this formula :

D({T1 to Tn}) = max(dh/MAXHFE, dv/MAXVBE)
where dh = max(hfe) - min(hfe)
      dv = max(vfe) - min(vfe).
