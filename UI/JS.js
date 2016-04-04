userName ="";

function login() {
    userName = document.getElementById('userlogin').value;
    if(userName != "") {
        document.getElementById('userlogin').value = "";
        document.getElementById('welcome').textContent = "Welcome, " + userName;
    }
    else {
        alert("Enter name!");
    }

}

function logout() {
    userName = "";
    document.getElementById('welcome').textContent = "Welcome to chat!";
}

function rename(){
    if (userName != "") {
        do{
            var newName = prompt("Write new name:", userName);
        }while(newName == "");

        var table = document.getElementById('messageTable');
        var length = table.querySelectorAll('tr').length;

        for (var i = 1; i < length; i++) {
            if(table.rows[i].cells[0].textContent == userName){
                table.rows[i].cells[0].textContent = newName;
            }
        };
        userName = newName;
        document.getElementById('welcome').textContent = "Welcome, " + userName;
    }
    else {
        alert("You must login!");
    }
}

function send(){
    if (userName != "") {
        var message = document.getElementById('usermessage').value;
        if(message != "") {
            document.getElementById('usermessage').value = "";

            var mycheckbox = document.createElement('input');
            mycheckbox.type = "checkbox";
            mycheckbox.id = "mycheckbox";
            var now = new Date();
            var temp = now.getMonth() + 1;

            var table = document.getElementById('messageTable');
            var tr = document.createElement('tr');
            var td0 = document.createElement('td');
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');

            td0 = mycheckbox;
            td1.innerHTML = userName;
            td2.innerHTML = message;
            td3.innerHTML = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() + " "
                + now.getDate() + "." + temp + "." + now.getFullYear();

            tr.appendChild(td0);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            table.appendChild(tr);
        }
        else {
            alert("Enter message!");
        }

    }else{
        alert("You must login!");
        document.getElementById('usermessage').value = "";
    }
}

function deleteMessage(){
    if (userName != "") {
        var table = document.getElementById('messageTable');
        var length = table.querySelectorAll('tr').length;
        var ok = table.querySelectorAll('input');
        count = 0;

        for (var i = 0; i < length - 1; i++) {
            if(ok[count].checked){
                if (userName == table.rows[i + 1].cells[0].textContent) {
                    table.deleteRow(i + 1);
                    i--;
                }else{
                    alert("It's another's message!");
                }
            }
            count++;
        };

    }
    else{
        alert("You must login!");
    }
}

function editMessage(){
    if (userName!="") {

        var table = document.getElementById('messageTable');
        var length = table.querySelectorAll('tr').length;
        var ok = table.querySelectorAll('input');

        for (var i = 0; i < length - 1; i++) {
            if (ok[i].checked){
                if (userName == table.rows[i +  1].cells[0].textContent) {
                    table.rows[i + 1].cells[1].textContent = prompt("Edit your message", table.rows[i + 1].cells[1].textContent);
                    ok[i].checked = false;
                }
                else{
                    alert("It's another's message!");
                }
            }
        }

    }
    else{
        alert("You must login!");
        document.getElementById('usermessage').value = "";
    }
}