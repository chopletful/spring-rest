const postlist = document.getElementById("post-list")
const addUser = document.getElementById("New_User_Form")

const delUser = document.getElementById("userDeleteModal")



$(document).on('click',"#edit_button",(e)=> {
    e.preventDefault();
    if ($(e.target).hasClass('btn')) {

        let user = {
            id: $("#id").val(),
            name: $("#firstname").val(),
            lastname: $("#lastname").val(),
            login: $("#email").val(),
            age: $("#age").val(),
            password: $("#password").val(),
            roles: getRole("#sel2m")
        }
        console.log(JSON.stringify(user));

        fetch("http://localhost:8080/api/addnew", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }).then(() => openTabById('home'))
            .then(() => restartAllUser())
            .then(() => $('#userEditModal').modal('hide'));
    }
})






restartAllUser();
// $(addUser).on('click',(e)=>{
//     e.preventDefault()
//     if($(e.target).hasClass('btn')){
//         alert("ololo")
//     }
//
// })

$(addUser).on('click',(e)=>{
    e.preventDefault();
    if($(e.target).hasClass('btn')) {

        let user = {
            name: $("#fn").val(),
            lastname: $("#ln").val(),
            login: $("#log").val(),
            age: $("#ag").val(),
            password: $("#pwd").val(),
            roles: getRole("#selectRole1")
        }
        console.log(JSON.stringify(user));

        fetch("http://localhost:8080/api/addnew", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }).then(() => openTabById('home'))
            .then(() => restartAllUser());
    }

});

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(), role: $(this).attr("name"), authority: $(this).attr("name")})
    });
    return data;
}

function openTabById(tab) {
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
}


function createTableRow(u) {
    // let userRole = "";
    // for (let i = 0; i < u.roles.length; i++) {
    //     userRole += " " + u.roles[i].role;
    // }
    return `<tr id="user_table_row">
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.lastname}</td>
            <td>${u.age}</td>
            <td>${u.username}</td>
            <td>${u.rolesAsString}</td>
            <td>
            <a  id="edit" value="${u.id}" href="/api/${u.id}" class="btn btn-info eBtn" >Edit</a>
            </td>
            <td>
            <a id="delete" href="/api/delete/${u.id}" value="${u.id}" class="btn btn-danger delBtn">Delete</a>
            </td>
            
        </tr>`;
}

function restartAllUser() {
    let UserTableBody = $("#user_table_body")

    UserTableBody.children().remove();

    fetch("api/allUsers")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableRow(item);
                UserTableBody.append(TableRow);

            }));
        }).catch(error => {
        console.log(error);
    });
}

// $(document).on('click',".logout", function(event){
//     event.preventDefault();
//     alert($(event.target))
//     // document.location.replace("/logout");
// })

document.addEventListener('click', function (event) {
    event.preventDefault()

    // if($(event.target).hasClass('userDeleteModal')){
    //     $('#userEditModal').modal('show');
    // }

    if(($(event.target).hasClass('logout'))){
        document.location.replace("/logout");
    }

    if ($(event.target).hasClass('btnUserTable')) {
        document.location.replace("/user");
    }

    // if ($(event.target).hasClass('delBtn')) {
    //     $('#userDeleteModal').modal('show');
    //
    //     // let href = $(event.target).attr("href");
    //     // fetch(href, {
    //     //     method: "DELETE",
    //     //     headers: {
    //     //         "Content-Type": "application/json;charset=utf-8"
    //     //     }
    //     // }).then(() => restartAllUser());
    // }
})

$(document).on('click', '#edit', function () {
    $.ajax("/api/edit", {
        method: "GET",
        data:
            {
                id: $(this).attr("value"),
            },
        dataType: "json",
        success: function (user) {
            $("#sel2m option:selected").removeAttr("selected");
            $('#id').val(user.id);
            $('#firstname').val(user.name);
            $('#lastname').val(user.lastname);
            $('#email').val(user.login);
            $('#age').val(user.age);
            $('#password').val(user.password);
            for(let role of user.roles){
                console.log("role" , role.id);
                $('#sel2m option[value=' + role.id + ']').attr('selected', true);
            }
            $('#userEditModal').modal('show');
        },
        error: function (e) {
            alert("error" + e);
        }
    })

})

$(document).on('click', '#delete', function () {
    $.ajax("/api/edit", {
        method: "GET",
        data:
            {
                id: $(this).attr("value"),
            },
        dataType: "json",
        success: function (user) {
            $("#sel2m1 option:selected").removeAttr("selected");
            $('#id1').val(user.id);
            $('#firstname1').val(user.name);
            $('#lastname1').val(user.lastname);
            $('#delete_button').val(user.id);
            $('#email1').val(user.login);
            $('#age1').val(user.age);
            $('#password1').val(user.password);
            for(let role of user.roles){
                console.log("role" , role.id);
                $('#sel2m1 option[value=' + role.id + ']').attr('selected', true);
            }
            $('#userDeleteModal').modal('show');
        },
        error: function (e) {
            alert("error" + e);
        }
    })

})

$(document).on('click',"#delete_button",function(event){
    $.ajax("/api/delete", {
        method: "DELETE",
        data:
            {
                id: $(this).attr("value"),
            },
        dataType: "json",
        success: function (userId) {
            restartAllUser();
            $('#userDeleteModal').modal('hide');

        },
        error: function (e) {
            alert("error" + e);
        }
    })
})






