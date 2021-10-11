----------------------- Q1
-- students who take some class on monday

select distinct e_id
from participer p where
exists 
(select titre from cours c where horaire like 'Lu%' and c_id=p.c_id )
order by e_id;


select distinct p.e_id 
from participer p join cours c using(c_id)
and c.horaire like 'Lu%';




------------------------------------------- Q2
select niveau, avg(age), count(e_id)
from etudiants
group by niveau
order by niveau;



-------------------------------------------- Q3

select e.e_nom                                                      
 from etudiants e, 
 participer pa, profs pr, cours c                              
where                                                                           
e.e_id = pa.e_id and 
pa.c_id = c.c_id and 
c.prof_id=pr.prof_id and 
 e.niveau='Licence 3' and 
pr.prof_nom='I. Boulala';

 
------------------------------------------- Q4

select max(age)
from etudiants 
where e_id in
(
(select  e_id from etudiants where 
programme='Histoire')
union
(select e_id from participer p,cours c,profs pr
where p.c_id = c.c_id and c.prof_id=pr.prof_id
and pr.prof_nom = 'I. Boulala'
 )
);


select max(age)
	from etudiants e
	where programme = 'Histoire'
	or e.e_id IN 
	(select distinct e.e_id
	from etudiants e, cours c, participer pr, profs p
	where e.e_id = pr.e_id
	and pr.c_id = c.c_id
	and c.prof_id = p.prof_id
	and p.prof_nom = 'I. Boulala');

------------------------------------------- Q5


select e_nom from etudiants where e_id
in 
(
select e.e_id from etudiants e, participer p1 , participer p2, cours c1, cours c2
where e.e_id = p1.e_id 
and e.e_id=p2.e_id 
and p2.c_id=c2.c_id
and p1.c_id=c1.c_id
and c1.c_id <> c2.c_id
and c1.horaire=c2.horaire
);


-- via exists query

select  e_nom from etudiants where e_id in
(
select p1.e_id 
from participer p1,cours c1
where p1.c_id=c1.c_id
and exists
(
select *
from participer p2,cours c2
where p2.c_id=c2.c_id
and p2.e_id = p1.e_id 
and c1.horaire=c2.horaire
and c1.c_id<>c2.c_id
)
);





---------------------Q6


SELECT DISTINCT E.e_nom 
FROM etudiants E 
WHERE E.e_id NOT IN (SELECT P.e_id from participer P);


------- solution alternative 

select e.e_id from etudiants e

minus

(select e_id from participer)

-- autre

select distinct E.e_nom
from Etudiant E
where not exists
      (
      select * from participer p where p.e_id=E.e_id
);


-----------------------------------------------Q7
-----Exo 2.3  --- age moyen par niveau, sauf pour Lic 1
SELECT e.niveau, AVG(e.age) 
FROM etudiants e
WHERE E.niveau <> 'Licence 1'
GROUP BY E.niveau;



--------------------------------------------------------Q8
 --- cours en R128 ou avec >= 15 etudiants

-- alternative: UNION

SELECT C.titre 
FROM Cours C 
WHERE C.salle = 'R128' 
OR C.c_id IN 
   (
   SELECT p.c_id
   FROM participer P group by P.c_id
   having COUNT (*) >= 5
);



----------------------------- Q9
-------(pour profs uniquement en R128, nom + nombre de ses cours ) -----------------------------

---- avec sous-requete correlative et NOT EXISTS

select c.prof_id, count(*) as nbcours
from cours c
where
not exists (select * from cours c2
    	   	     where c2.prof_id= c.prof_id
		     and c2.salle <> 'R128')

group by c.prof_id;

---- avec having every

SELECT P.prof_id, P.prof_nom, COUNT(*) AS Nbcours 
FROM  Profs P, Cours  C 
WHERE P.prof_id = C.prof_id 
GROUP BY P.prof_id, P.prof_nom
HAVING EVERY ( C.salle = 'R128' );



--------------Q 10--------------------------------
select distinct E.e_nom 
from Etudiants E 
where E.e_id in 
      (select P.e_id   --- id de l'etudiant ayant suivi le plus de cours
      from Participer P 
      group by P.e_id
      having count (*) >= all 
            (select count(*) from Participer P2 group by P2.e_id ) --- nombre de cours par etudiant
);

--- ou bien plus facile avec une vue
create view coursParEtudiant(e_id,nbr) as 
(select e_id, count(*)         
  from participer                                     
group by e_id                                        
);

select e_nom from etudiants where e_id in 
(select e_id from coursParEtudiant where nbr = (select max(nbr) from coursParEtudiant));                   



------------- Q11
-- ids of active students (who take some class), but none on monday

select e_nom from etudiants where e_id in
       (
       (select distinct e_id from participer)-- participants d'un cours, sauf
       	       except  -- participants d'un cours du lundi
	       (select distinct p.e_id 
	       from cours c join participer p on c.c_id=p.c_id
	        where horaire like 'Lu%')
		);


-------

select e.e_nom from etudiants e where e.e_id in
(
select distinct p.e_id
from participer p 
where not exists 
      (select * from cours c join participer p2 on c.c_id=p2.c_id
      where 
      c.horaire like 'Lu%'  and p.e_id=p2.e_id));


-- Q12


CREATE VIEW nbr_etudiants (cours, nombre) AS
SELECT c1.c_id, count(e_id)
FROM participer p1 right join cours c1 on (p1.c_id=c1.c_id)
group by c1.c_id;


SELECT AVG(nombre)
from nbr_etudiants;
