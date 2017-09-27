/**
 * 
 */
//点击创建表更新创表界面
function fresh() {
	$("#tableName").nextUntil("#add_btn").remove();
    $("#tableName").after('<li class="list-group-item" style="list-style: none">' +
        '<span class="glyphicon glyphicon-remove" style="font-size: 150%;vertical-align:middle" onclick="deleteCol(this)"></span>' +
        '列族名：' +
        '<input type="text" name="cols"  name="colName"/>' +
        '<span> Add a column property</span><span class="glyphicon glyphicon-plus-sign" style="font-size: 150%;vertical-align:middle;margin-left: 5px" onclick="addProperty(this)"></span>' +
        '</li>')
}

//点击加号添加列属性
function addProperty(obj){
	if($(obj).prev().text() == " Add a column property"){
        $(obj).prev().remove();
        $(obj).before('</br></br>' + '<span style="float:left;font-size: 20px">property:</span>');
        $(obj).before('<div id="selectStyle">' +
            '<select id="select">' +
            '<option>MaxVersion</option>' +
            '<option>inmemory</option>' +
            '<option>blocksize</option>' +
            '</select>' +
            '</div>'
        );
        $(obj).before('<input type="text" name="property"/>');
	}else {
        $(obj).before('<span class="glyphicon glyphicon-minus-sign" style="font-size:150%;vertical-align:middle;margin-left:5px" onclick="deleteProperty(this)"></span>');
        $(obj).before('</br></br>' + '<span style="float:left;font-size: 20px">property:</span>');
        $(obj).before('<div id="selectStyle">' +
            '<select id="select">' +
            '<option>MaxVersion</option>' +
            '<option>MinVersion</option>' +
            '<option>Inmemory</option>' +
            '<option>BlockSize</option>' +
            '<option>TimeToLive</option>' +
            '<option>ComressionType</option>' +
            '</select>' +
            '</div>'
        );
        $(obj).before('<input type="text" name="property"/>');
    }
	// $(obj).before('<span class="glyphicon glyphicon-minus-sign" style="font-size: 150%;vertical-align: middle"></span>');
}

function deleteProperty(obj){
	for(var i = 0; i < 5; i++)
        $(obj).prev().remove();
    $(obj).remove();
}

function addcol()
{
 var s=document.getElementById('ul_addtable');
 var t=s.childNodes.length;
 var li= document.createElement("li");
 li.innerHTML='' +
	 '<li class="list-group-item" style="list-style: none">' +
	 '<span class="glyphicon glyphicon-remove" style="font-size: 150%;vertical-align:middle" onclick="deleteCol(this)"></span>' +
	 '列族名：' +
	 '<input type="text" name="cols"  name="colName"/>' +
	 '<span> Add a column property</span><span class="glyphicon glyphicon-plus-sign" style="font-size: 150%;vertical-align:middle;margin-left: 5px" onclick="addProperty(this)"></span>' +
	 // '<span class="glyphicon glyphicon-plus-sign" style="float:right;font-size: 150%"></span>' +
	 // '<span class="glyphicon glyphicon-minus-sign" style="float:right;font-size: 150%"></span>' +
	 '</li>';
 s.insertBefore(li,s.childNodes[t-2]);
}

function deleteCol(obj){
	var Col = $(obj).parent();
	Col.remove();
}
function submit(){
	var tablename= $("input[name='tablename']").val();
	// var cols=document.getElementsByName('cols');

	if(tablename == ""){
		alert("表名或列族名为空");
	}else{
        var cols_json = "[";
        var cols = $("#tableName").nextUntil("#add_btn");
	    cols.each(function (i,val) {
            var col_name = $(this).find("input[name='cols']").val();
            cols_json += "{";
            cols_json += "\"" + "col_name" + "\""+ ":" + "\"" + col_name + "\"";
            //判断是否有列属性
            var properties = $(this).find("select");
            if(properties.length >= 1) {
                $(this).find("select").each(function (i, val) {
                    var col_properties_name = $(this).val();
                    var col_properties_value = $(this).parent().next().val();
                    cols_json += ",\"" + col_properties_name + "\"" + ":"
                        + "\"" + col_properties_value + "\",";
                });
                cols_json = cols_json.substring(0, cols_json.length - 1);
            }
            cols_json += "},"
        });
	    cols_json = cols_json.substring(0, cols_json.length - 1);
        cols_json += "]";
        alert(cols_json);
        $.ajax({
            type: "POST",
            async : true,
            url: "addTable?" + "tableName=" + tablename + "&cols=" + cols_json,
            dataType: "json",
            success: function () {
                alert("success");
            },
            error: function () {
                alert("error");
            }
        });

	}
	
}