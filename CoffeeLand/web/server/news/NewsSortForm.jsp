<%-- 
    Document   : NewsSortForm
    Created on : Feb 13, 2024, 12:07:15 PM
    Author     : acer
--%>
<form action="/CoffeeLand/control">
    <input type="hidden" name="colName" value="<%=colName%>">
    <input type="hidden" name="sortType" value="ASC">
    <input type="hidden" name="requestAction" value="Sort News"/>
    <button type="submit" 
            class="btn btn-outline-infolist btn-sm p-0 justify-content-center ms-1"
            style="color: black; border: none"
            title="Ascending Order"
    >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-double-down" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M1.646 6.646a.5.5 0 0 1 .708 0L8 12.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
          <path fill-rule="evenodd" d="M1.646 2.646a.5.5 0 0 1 .708 0L8 8.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
        </svg>
    </button>
</form>
    
<form action="/CoffeeLand/control">
    <input type="hidden" name="colName" value="<%=colName%>">
    <input type="hidden" name="sortType" value="DESC">
    <input type="hidden" name="requestAction" value="Sort News"/>
    <button type="submit" 
            class="btn btn-outline-infolist btn-sm p-0 justify-content-center"
            style="color: black; border: none"
            title="Descending Order"
    >
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-double-up" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M7.646 2.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 3.707 2.354 9.354a.5.5 0 1 1-.708-.708l6-6z"/>
            <path fill-rule="evenodd" d="M7.646 6.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 7.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
        </svg>
    </button>
</form>

