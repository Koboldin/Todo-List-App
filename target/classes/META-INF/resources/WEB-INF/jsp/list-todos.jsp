<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     


<body>
	
	<div class="container">
		<div>Welcome ${name}!</div>
		<hr>
		<h1>Deine Todo-Liste</h1>
		<!--JSTL: Einfügen der Daten aus ArrayList todos in Tabelle -->
		<table class="table">
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Done?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td>
      <fmt:parseDate value="${todo.targetDate}" pattern="yyyy-MM-dd" var="targetDate" type="date"/>
      <fmt:formatDate value="${targetDate}" pattern="dd.MM.yyyy" />
    </td>
						<td>${todo.done}</td>
						<td><a href="update-todo?id=${todo.id}"
							class="btn btn-success">Update</a></td>
						<td><a href="delete-todo?id=${todo.id}"
							class="btn btn-warning">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Todo</a>

	</div>
	<script scr="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

</body>
<%@ include file="common/footer.jspf" %>
</html>