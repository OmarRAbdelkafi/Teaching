import psycopg2

def DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST):

    try:
        conn = psycopg2.connect(dbname=DB_NAME, user=DB_USER, password=DB_PASS, host=DB_HOST)
    except:
        print("I am unable to connect to the database")

    cur = conn.cursor()

    '''
    try:
        cur.execute("select * from etudiants")
    except:
        print("I can't select from etudiants")

    rows = cur.fetchall()

    print("\nshow all the students names :\n")
    for r in rows:
        print("-", r[1]) #the second column (index 1) in the table is for names

    print("The first name is : ", rows[0][1]) #[ligne][columns]
    print("\n")
    '''

    try:
        cur.execute("select distinct e_id from participer p where exists (select titre from cours c where horaire like 'Lu%' and c_id=p.c_id) order by e_id;")
    except:
        print("I can't execute the query Num 1")
    rows = cur.fetchall()
    print("id students who take some class on monday:")
    print(rows)
    print("\n")

    try:
        cur.execute("select niveau, avg(age), count(e_id) from etudiants group by niveau order by niveau;")
    except:
        print("I can't execute the query Num 2")
    rows = cur.fetchall()
    print("Avg age and count by lvl:")
    for r in rows:
        print("-", r)
    print("\n")

    print("\n----commit in the DB----\n")
    conn.commit()

    cur.close()

    conn.close()
