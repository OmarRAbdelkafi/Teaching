import psycopg2

def DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST):

    try:
        conn = psycopg2.connect(dbname=DB_NAME, user=DB_USER, password=DB_PASS, host=DB_HOST)
    except:
        print("I am unable to connect to the database")

    cur = conn.cursor()

    print("Tables creation...")
    cur.execute("create table etudiants(e_id int primary key, e_nom varchar(30), programme varchar(25), niveau varchar(10), age int);")
    cur.execute("create table profs(prof_id int primary key, prof_nom varchar(30), lab_id int );")
    cur.execute("create table cours(c_id int primary key, titre varchar(40) unique, horaire varchar(20), salle varchar(10), prof_id int, foreign key(prof_id) references profs);")
    cur.execute("create table participer(e_id int, c_id int, primary key(e_id,c_id), foreign key(e_id) references etudiants, foreign key(c_id) references cours(c_id));")

    print("\n----commit in the DB----\n")
    conn.commit()

    cur.close()

    conn.close()
