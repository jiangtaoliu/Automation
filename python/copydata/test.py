cursor.execute('INSERT IGNORE INTO ' + tablename + ' (station_name, \
        station_no, station_id, station_latitude, station_longitude) \
        VALUES("%s", "%s", "%s", "%s", "%s")', (925/1, 6456001, 6456001, -36.59414, 174.652153))


cursor.execute('INSERT INTO station (station_name, \
    station_no, station_id, station_latitude, station_longitude) \
    VALUES("%s", "%s", "%s", "%s", "%s"), \
    (row["station_name"], row["station_no"], row["station_id"], row["station_latitude"], row["station_longitude"]))
