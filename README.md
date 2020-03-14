# h2access - Simplified SQL Database usage for Custom NPCs
Create or load a database at the file "D:/H2databases/test". Username is user, password is password by writing either
```js
    var db1 = Java.type("org.baito.h2access.H2Access").create("D:/H2databes/test","user","password");
```
or writing
```js
  var dbClass = Java.type("org.baito.h2access.H2Access");
  var db1 = new dbClass("D:/H2databes/test","user","password");
```

Available methods:

**query(String query)** - Used for running queries that return values. Returns a [ResultSet](https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html)

**update(String update)** - Used for creating tables, inserting values, dropping tables, etc items that don't return values.

**lupdate(String update)** - Used for creating tables, inserting values, dropping tables, etc items for larger quanities.

**close()** - Closes the connection. Highly recommend using this when the connection is not needed.

**open()** - Opens the connection again. Make sure to close conenctions once you're not using them to ensure you can
access them from external sources.

Once a database has been loaded or created, you can run queries like so:
```js
  db1.update("CREATE TABLE Players(
    UUID VARCHAR(30),
    Money INT,
    PRIMARY KEY (UUID)
  );");
  db1.update("INSERT INTO Players(UUID, Money) VALUES ('abc-123',200),('zyx-987',10000);")
  var results = db1.query("SELECT * FROM Players");
  while (results.next) {
    event.player.message(results.getString("UUID"));
  }
```
