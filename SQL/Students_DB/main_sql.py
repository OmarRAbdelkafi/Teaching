DB_NAME = "pythontest"
DB_USER = "postgres"
DB_PASS = "root"
DB_HOST = "localhost"

import creat_tab as CT
import drop_tab as DR
import fill_tab as FI
import queries as Q

#DR.DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST)

#CT.DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST)
#FI.DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST)

Q.DB_students(DB_NAME, DB_USER, DB_PASS, DB_HOST)
