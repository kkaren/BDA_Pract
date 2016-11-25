<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%--
  the values for "arrow=xxx" are the names of the images jpivot/jpivot/table/arrow-xxx.gif
--%>


<jp:mondrianQuery id="query01" jdbcDriver="org.postgresql.Driver" jdbcUrl="jdbc:postgresql://localhost:5432/VolaUB" catalogUri="/WEB-INF/queries/VolaUB.xml"  jdbcUser="postgres" jdbcPassword="db1" connectionPooling="false">select {[Measures].[Total reservations], [Measures].[Total passengers], [Measures].[Total payment amount]} on columns, [passenger] on rows from [Reservation]</jp:mondrianQuery>

<c:set var="title01" scope="session">Arrows</c:set>
