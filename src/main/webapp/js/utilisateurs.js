function getListeUtilisateurs() {
    $.ajax({
        type: "POST",
        url: "ajax",
        data: {
            'categorie': "getListeUtilisateurs"

        },
        success: function(html) {
            $("#displayListeUtilisateurs").html(html).show();
            $("#table").bootstrapTable();
        }
    });
}

$(document).ready(function() {
    //$("#displayListeUtilisateurs").html('<tr><td colspan="5"><strong>Chargement...</strong></td></tr>');

    //getListeUtilisateurs();



    $("#refreshListeUtilisateurs").click(function (){
        $("#displayListeUtilisateurs").html('<tr><td colspan="5"><strong>Chargement...</strong></td></tr>');
        getListeUtilisateurs();
    });

});