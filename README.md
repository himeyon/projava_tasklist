### projava_tasklist

このリポジトリのプログラムは[プロになるJava](https://gihyo.jp/book/2022/978-4-297-12685-8)の第6部(Webアプリケーション開発)をもとにしたものです。

### データベース

書籍ではH2が利用されていますが、このリポジトリではPostgres14を利用しました。  
データベース作成用のDDLは以下のとおりです。

```sql
CREATE ROLE projava LOGIN
PASSWORD 'pr0J@va'
NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
```

```sql
CREATE DATABASE projava
WITH OWNER = projava
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'C'
LC_CTYPE = 'C'
TEMPLATE = 'template0'
CONNECTION LIMIT = -1;
```
