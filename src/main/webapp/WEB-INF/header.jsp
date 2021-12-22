<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="<c:url value="/css/custom.css"/>" rel="stylesheet">

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

