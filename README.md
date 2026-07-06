# MyBatis Practice

## What is MyBatis?

**MyBatis** is a lightweight Java framework that simplifies working with
databases by acting as a bridge between your Java application and SQL. It helps
you write and execute SQL queries efficiently, map database records to Java
objects, and handle complex database operations with minimal code.

### In Simple Terms

- You focus on **SQL** for database interactions.
- MyBatis handles the boring parts, like mapping query results to objects and
  vice versa.
- It’s perfect if you want control over your SQL without the overhead of
  managing boilerplate code.

## Why Use MyBatis?

- **Custom SQL**: Write your own queries for complete control.
- **Easy Mapping**: Automatically maps database results to Java objects.
- **Flexible**: Supports complex queries and dynamic SQL generation.
- **Lightweight**: No heavy setup or overhead like some ORMs (e.g., Hibernate).

---

### Resources

- [Official MyBatis Documentation](https://mybatis.org/mybatis-3/)
- [GitHub Repository](https://github.com/mybatis/mybatis-3)

---

## Table of Contents

- [MyBatis Settings](#mybatis-settings)
  - [Add Config](#add-config)
  - [Add Mapper](#add-mapper)
- [Create MyBatis Config](#create-mybatis-config)
- [Create Mapper](#create-mapper)
- [Aliases](#aliases)
- [Data Types](#data-types)
- [Dynamic If](#dynamic-if)
- [Dynamic For](#dynamic-for)
- [Trouble Shooting](#trouble-shooting)

---

## MyBatis Settings

First setup Eclipse IDE for Java web development.
Check [unemotioned/bulletin-board](https://github.com/unemotioned/bulletin-board).

### Window / Preferences / XML / XML Catalog

click on `User Specified Entries` and then `Add...`

> NOTE: make sure there is no trailing space at the end

#### Add Config

- Location:

```text
http://mybatis.org/dtd/mybatis-3-config.dtd
```

- Key:

```text
-//mybatis.org//DTD Config 3.0//EN
```

#### Add Mapper

- Location:

```text
http://mybatis.org/dtd/mybatis-3-mapper.dtd
```

- Key:

```text
-//mybatis.org//DTD Mapper 3.0//EN
```

---

### Window / Preferences / XML (Wild Web Developer)

Check `download external resources like referenced dtd, xsd` checkbox

---

## Create MyBatis Config

Specify connection with the database

1. Under `src/main` create new `source folder` named `resources`
2. Create `XML file` under the resource folder named `mybatis-config`
3. Check `create file using a DTD or XML scheme file` checkbox
4. Select `xml catalog entry`
5. Click `User Specified Entries` select the one with `config`
6. add the following inside `<configuration>` tag

```xml
<settings>
    <setting name="jdbcTypeForNull" value="NULL" />
</settings>

<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC" />
        <dataSource type="POOLED">
            <property name="driver" value="oracle.jdbc.driver.OracleDriver" />
            <property name="url" value="jdbc:oracle:thin:@127.0.0.1:1521:xe" />
            <property name="username" value="mybatis_test" />
            <property name="password" value="1234" />
        </dataSource>
    </environment>
</environments>
```

---

## Create Mapper

Mapper files with XML extensions are where the queries will be written.

1. Create new `Folder` under the resources named `mapper`
2. Create `XML File` under the mapper folder named `member-mapper`
3. Check `create file using a DTD or XML scheme file` checkbox
4. Select `XML Catalog Entry`
5. Click `User Specified Entries` select the one with `mapper`

---

## Aliases

At `resource/mybatis-config.xml` add the following code inside the
`configuration` tag after `settings` tag

```xml
<typeAliases>
    <typeAlias type="kr.or.iei.member.model.vo.Member" alias="member" />
</typeAliases>
```

Now for `parameterType` and `resultType` you can now use `member` instead of
full path

---

## Data Types

| Type   | MyBatis Type     |
| ------ | ---------------- |
| int    | \_int            |
| short  | \_short          |
| byte   | \_byte           |
| float  | \_float          |
| double | \_double         |
| String | string           |
| Map    | map              |
| Hash   | hashmap (or map) |
| Date   | date             |

---

### Dynamic IF

- Selecting `'a'` after if tags ensure the query remains valid by providing a
  constant fallback column, preventing errors when no dynamic columns are selected.

```xml
<select id="selDynamicIfTest"
        parameterType="member"
        resultType="member">
    select
        <if test="sFlag1 != null and sFlag1 == 'on'">
            member_no    as "memberNo",
        </if>
        <if test="sFlag2 != null and sFlag2 == 'on'">
            member_id    as "memberId",
        </if>
        <if test="sFlag3 != null and sFlag3 == 'on'">
            member_name  as "memberName",
        </if>
        <if test="sFlag4 != null and sFlag4 == 'on'">
            member_email as "memberEmail",
        </if>
        <if test="sFlag5 != null and sFlag5 == 'on'">
            member_phone as "memberPhone",
        </if>
        'a'
    from tbl_member
</select>
```

---

### Dynamic FOR

- `collection`: arguments type
- `item`: name to use inside `foreach` tag
- `open`: before the first element
- `separator`: between elements
- `close`: after the last element

```xml
<select id="selDynamicForTest"
        parameterType="map"
        resultType="member">
    select
        member_no    as "memberNo",
        member_id    as "memberId",
        member_pw    as "memberPw",
        member_name  as "memberName",
        member_email as "memberEmail",
        member_phone as "memberPhone",
        member_addr  as "memberAddr",
        member_level as "memberLevel",
        enroll_date  as "enrollDate"
    from tbl_member
    where member_id in
        <foreach collection="array"
                 item="id"
                 open="("
                 separator=","
                 close=")">
            #{id}
        </foreach>
</select>
```

---

## Trouble Shooting

### java.io.IOException: Could not find resource mybatis-config.xml

- `resource` folder path not being registered in in `.classpath`

1. Package Explorer > right click `resource` folder
2. Select `Build Path` > `Use as Source Folder`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
    ...
    <classpathentry kind="src" path="resource"/>
    ...
</classpath>
```
