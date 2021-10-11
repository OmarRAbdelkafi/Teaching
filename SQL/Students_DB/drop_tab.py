import psycopg2

def DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST):

    try:
        conn = psycopg2.connect(dbname=DB_NAME, user=DB_USER, password=DB_PASS, host=DB_HOST)
    except:
        print("I am unable to connect to the database")

    cur = conn.cursor()

    print("Drop Tables...")
    cur.execute("drop table participer cascade;")
    cur.execute("drop table cours cascade;")
    cur.execute("drop table etudiants cascade;")
    cur.execute("drop table profs cascade;")

    print("\n----commit in the DB----\n")
    conn.commit()

    cur.close()

    conn.close()
