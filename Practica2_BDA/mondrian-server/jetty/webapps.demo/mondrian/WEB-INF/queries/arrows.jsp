<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%--
  the values for "arrow=xxx" are the names of the images jpivot/jpivot/table/arrow-xxx.gif
--%>


<jp:mondrianQuery id="query01" jdbcDriver="org.postgresql.Driver" jdbcUrl="jdbc:postgresql://localhost:5432/postgres" catalogUri="/WEB-INF/queries/VolaUB.xml"  jdbcUser="postgres" jdbcPassword="db1" connectionPooling="false">select {[Measures].[num_reservations]} on columns, [passengers] on rows from Reservations</jp:mondrianQuery>

<c:set var="title01" scope="session">Arrows</c:set>
