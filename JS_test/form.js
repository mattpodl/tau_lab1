export function formDocument(number, twoCharText) {
    var x = document.createElement("INPUT");
    x.setAttribute("type", "number");
    x.setAttribute("value", number.value);
    x.setAttribute("id", number.id);
    document.body.appendChild(x);

    var aOBJ = document.getElementById(number.id);

        if (aOBJ.value > 0) {
            document.getElementById(number.id).className = "correct";
        } else {
            document.getElementById(number.id).className = "incorrect";
        }

     if(twoCharText != undefined) {
        var y = document.createElement("INPUT");
        y.setAttribute("type", "text");
        y.setAttribute("value", twoCharText.value);
        y.setAttribute("id", twoCharText.id);
        document.body.appendChild(y);
        var bOBJ = document.getElementById(twoCharText.id);
        if (bOBJ.value.length > 2){
            document.getElementById(twoCharText.id).className = "incorrect";
        } else {
            document.getElementById(twoCharText.id).className = "correct";
        }
     }
}
