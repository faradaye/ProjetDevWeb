<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src='<c:url value="/js/jquery-3.6.0.min.js"/>'></script>
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/css/custom.css"/>" rel="stylesheet">
<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>

<script language="javascript" type="text/javascript">
    function Deconnexion(){
        var confDeco = confirm("Voulez vous vraiment vous déconnecter?");
        if(confDeco) document.location.href = "deconnexion";
    }

    $(document).ready(function(){




        var down = false;

        $('#bell').click(function(e){

            var color = $(this).text();
            if(down){

                $('#box').css('height','0px');
                $('#box').css('opacity','0');
                down = false;
            }else{

                $('#box').css('height','auto');
                $('#box').css('opacity','1');
                down = true;

            }

        });

    });
</script>

