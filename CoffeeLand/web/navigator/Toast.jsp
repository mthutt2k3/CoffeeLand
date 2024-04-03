<!--Toast-->
<%
    session = request.getSession(false);
    String alert = null;
    if (session.getAttribute("alert") != null) {
       alert = (String) session.getAttribute("alert");
       session.removeAttribute("alert");
    }
%>

<% if (alert != null && !alert.isEmpty()) { %>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div class="toast align-items-center text-white bg-primary border-0 p-2" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
          <div class="toast-body">
            <%= alert %>
          </div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<%}%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const toastContent = document.querySelector('.toast');
        const toast = new bootstrap.Toast(toastContent);
        toast.show();
    });
</script>
