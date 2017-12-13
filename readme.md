#Authentication Setup

### Wildfly Setup

security-domain in `[wildfly]\standalone\configuration\standalone.xml` hinzufügen:
```xml
    <subsystem xmlns="urn:jboss:domain:security:1.2">
        <security-domains>
            <security-domain name="FilmManagementSD">
                <authentication>
                    <login-module code="Database" flag="required">
                        <module-option name="dsJndiName" value="java:jboss/datasources/FilmManagementDS"/>
                        <module-option name="principalsQuery" value="SELECT password AS Password FROM t_user WHERE username = ?"/>
                        <module-option name="rolesQuery" value="SELECT r.rolename, 'Roles' AS Roles FROM t_user u INNER JOIN t_user_role ur ON u.userid = ur.userid INNER JOIN t_role r ON ur.roleid = r.roleid WHERE u.username = ?"/>
                        <module-option name="hashAlgorithm" value="SHA-512"/>
                        <module-option name="hashEncoding" value="hex"/>
                    </login-module>
                </authentication>
            </security-domain>
            ...
```

### DB Setup

- cmd im Ordner db-setup starten
- `psql -U admin -d postgresdb_filmmanagement -f create-tables-for-security.sql`