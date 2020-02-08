<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/head.jsp" />
    </head>


    <body>
        <div class="container-fluid">
            <div class="jumbotron">
                    <p>Search City: <input id="searchTerm" type="text" name="searchTerm" onkeyup="search()"/></p>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr class="row">
                        <th class="col-md-2">Street</th>
                        <th class="col-md-2">Number</th>
                        <th class="col-md-2">Postal code</th>
                        <th class="col-md-2">City</th>
                        <th class="col-md-2">GeoLocation</th>
                        <th class="col-md-1">Distance</th>
                        <th class="col-md-1">Type</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    <c:forEach items="${locations}" var="temp">
                        <tr class="row" >
                            <td class="col-md-2">
                                ${temp.address.street} 
                            </td>
                            
                             <td class="col-md-2">
                                ${ temp.address.housenumber}
                            </td>
                            
                             <td class="col-md-2">
                                ${temp.address.postalcode}
                            </td>
                            
                             <td class="col-md-2">
                                ${temp.address.city}
                            </td>
                            
                           
                            <td class="col-md-2">
                                ${temp.address.geoLocation.lat}  , ${temp.address.geoLocation.lng}
                            </td>
                            <td class="col-md-1">
                                ${temp.distance}"
                            </td>
                            <td class="col-md-1">
                                ${temp.type}"
                            </td>

                        </tr>
                         </c:forEach>
                    </tbody>
                </table>
            </div>
            <script>
                function search() {
                    var input, filter, table, tr, td, i, txtValue;
                    input = document.getElementById("searchTerm");
                    filter = input.value.toUpperCase();
                    table = document.getElementById("tbody");
                    tr = table.getElementsByTagName("tr");
                    for (i = 0; i < tr.length; i++) {
                        td = tr[i].getElementsByTagName("td")[3];
                        if (td) {
                            txtValue = td.textContent || td.innerText;
                             if (txtValue.toUpperCase().indexOf(filter) > -1) { 
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = "none";
                            }
                        }
                    }
                }
            </script>
        </body>
    </html>