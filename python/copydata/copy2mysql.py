import csv
import MySQLdb

mydb = MySQLdb.connect(host='192.168.1.119',
    user='root',
    passwd='cics@unitec',
    db='station')
cursor = mydb.cursor()

csv_data = csv.reader(file('/Users/jetlau/Downloads/stationlist.csv'))
for row in csv_data:
    cursor.execute('INSERT INTO stationlist(station_name, \
          station_no, station_id, station_latitude, station_longitude)' \
          'VALUES("%s", "%s", "%s", "%s", "%s")',
          row)
#close the connection to the database.
mydb.commit()
cursor.close()
print("Done")
