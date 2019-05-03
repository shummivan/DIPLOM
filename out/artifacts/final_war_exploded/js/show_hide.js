
function getObj(id){
    return document.getElementById(id);
}

function showHideTr(trId){
    var o=getObj(trId);
    if(o.style.display=='none'){
        o.style.display='table-row';
    }else{
        o.style.display='none';
    }
}

function showHide(action, id1, id2){
    var o;
    if(action){
        o=getObj(id1);
        o.style.display='none';
        if(id2!=''){
            o=getObj(id2);
            o.style.display='none';
        }
    }else{
        o=getObj(id1);
        o.style.display='';
        if(id2!=''){
            o=getObj(id2);
            o.style.display='';
        }
    }
}
