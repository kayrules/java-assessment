# Wildfly configured with MySQL data source
FROM metz/wildfly

# Expose the default's application port
EXPOSE 8080

# Copy the war file to the deployments folder
COPY ./target/assessment-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/assessment-0.0.1.war