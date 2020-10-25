CREATE TABLE positions (
  id SERIAL PRIMARY KEY,
  device VARCHAR(128),
  geometry GEOMETRY(Point, 4326),
  eventtime TIMESTAMP
);
