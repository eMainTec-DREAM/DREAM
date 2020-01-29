function eXcell_acedp(cell)//the eXcell name is defined here
{ 
	if (cell){                // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
        //eXcell_ed.call(this); //uses methods of the "ed" type
    }
	this.edit=function(){
		this.cell.atag=(!this.grid.multiLine)?"INPUT":"TEXTAREA";
		this.val=this.getValue();
		this.obj=document.createElement(this.cell.atag);
		this.obj.setAttribute("autocomplete","off");
		this.obj.style.height=(this.cell.offsetHeight-(_isIE?4:4))+"px";
		this.obj.className="dhx_combo_edit";
		this.obj.wrap="soft";this.obj.style.textAlign=this.cell.style.textAlign;
		this.obj.onclick=function(c){(c||event).cancelBubble=true};
		this.obj.onmousedown=function(c){(c||event).cancelBubble=true};
		this.obj.onselectstart=function(c){if(!c){c=event}c.cancelBubble=true};
		this.obj.value=this.val;
		this.cell.innerHTML="";
		
		var _span = document.createElement('span');
		var _a =document.createElement('a');
		_a.onclick=function(c){(c||event).cancelBubble=true};
		var _p =document.createElement('p');
		var _div =document.createElement('div');
		_p.className="open_spop";
		_div.className="input_grid";
		_a.appendChild(_span);
		_p.appendChild(_a);
		_div.appendChild(this.obj);
		_div.appendChild(_p);

		this.cell.appendChild(_div);

		$('input,select').keypress(function(event) {return event.keyCode != 13;}); 
		//Object return and set AC
		if(typeof setGridAc == "function") setGridAc(this.grid, this.cell);

		$(this.cell).find('input').focus();
		


		return true;
    }
	this.detach=function(){
         // sets the new value
        // this.setValue($(this.cell).find('input').val()); 
         if(typeof $(this.cell).find('input').val() != "undefined")
         	this.setCValue($(this.cell).find('input').val(),$(this.cell).find('input').val());  
         
         return true; // compares the new and the old values
    } 
    this.setValue=function(val){
        this.setCValue(val,val);  
    }
}
	
eXcell_acedp.prototype = new eXcell;// nests all other methods from the base class

function eXcell_aced(cell)//the eXcell name is defined here
{ 
	if (cell){                // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
        //eXcell_ed.call(this); //uses methods of the "ed" type
    }
	this.edit=function(){
		this.cell.atag=(!this.grid.multiLine)?"INPUT":"TEXTAREA";
		this.val=this.getValue();
		this.obj=document.createElement(this.cell.atag);
		this.obj.setAttribute("autocomplete","off");
		this.obj.style.height=(this.cell.offsetHeight-(_isIE?4:4))+"px";
		this.obj.className="dhx_combo_edit";
		this.obj.wrap="soft";this.obj.style.textAlign=this.cell.style.textAlign;
		this.obj.onclick=function(c){(c||event).cancelBubble=true};
		this.obj.onmousedown=function(c){(c||event).cancelBubble=true};
		this.obj.onselectstart=function(c){if(!c){c=event}c.cancelBubble=true};
		this.obj.value=this.val;
		this.cell.innerHTML="";
		
		var _div =document.createElement('div');
		_div.className="input_grid";
		_div.appendChild(this.obj);

		this.cell.appendChild(_div);
		
		$('input,select').keypress(function(event) {return event.keyCode != 13;}); 
		
		//Object return and set AC
		if(typeof setGridAc == "function") setGridAc(this.grid, this.cell);

		$(this.cell).find('input').focus();
		

		return true;
    }
	this.detach=function(){
         // sets the new value
        // this.setValue($(this.cell).find('input').val()); 
         if(typeof $(this.cell).find('input').val() != "undefined")
         	this.setCValue($(this.cell).find('input').val(),$(this.cell).find('input').val());  
         
         return true; // compares the new and the old values
    }
    this.setValue=function(val){
        this.setCValue(val,val);  
    }
}
	
eXcell_aced.prototype = new eXcell;// nests all other methods from the base class

function eXcell_ednum(cell)//the eXcell name is defined here
{ 
	if (cell){                // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
        //eXcell_ed.call(this); //uses methods of the "ed" type
    }
	this.edit=function(){
		this.cell.atag=(!this.grid.multiLine)?"INPUT":"TEXTAREA";
		this.val=this.getValue();
		this.obj=document.createElement(this.cell.atag);
		this.obj.setAttribute("autocomplete","off");
		this.obj.style.height=(this.cell.offsetHeight-(_isIE?4:4))+"px";
		this.obj.className="dhx_combo_edit num";
		this.obj.wrap="soft";this.obj.style.textAlign=this.cell.style.textAlign;
		this.obj.onclick=function(c){(c||event).cancelBubble=true};
		this.obj.onmousedown=function(c){(c||event).cancelBubble=true};
		this.obj.onselectstart=function(c){if(!c){c=event}c.cancelBubble=true};
		this.obj.value=setNumberFormat(this.val);
		this.cell.innerHTML="";
		
		var _div =document.createElement('div');
		_div.className="input_grid";
		_div.appendChild(this.obj);

		this.cell.appendChild(_div);

		$('input,select').keypress(function(event) {return event.keyCode != 13;}); 
		//Object return and set AC
		if(typeof setGridAc == "function") setGridAc(this.grid, this.cell);
		$(this.cell).find('input').focus();
		
		setNumberFmt();

		return true;
    }
	this.detach=function(){
         // sets the new value
        // this.setValue($(this.cell).find('input').val()); 
         if(typeof $(this.cell).find('input').val() != "undefined")
         	this.setCValue($(this.cell).find('input').val().split(",").join(),$(this.cell).find('input').val().split(",").join());  
         
         return true; // compares the new and the old values
    }
    this.setValue=function(val){
        this.setCValue(setNumberFormat(val),val);  
    }
}

eXcell_ednum.prototype = new eXcell;// nests all other methods from the base class


function eXcell_edtynum(cell)//the eXcell name is defined here
{ 
	if (cell){                // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
        //eXcell_ed.call(this); //uses methods of the "ed" type
    }
	this.edit=function(){
		this.cell.atag=(!this.grid.multiLine)?"INPUT":"TEXTAREA";
		this.val=this.getValue();
		this.obj=document.createElement(this.cell.atag);
		this.obj.setAttribute("autocomplete","off");
		this.obj.style.height=(this.cell.offsetHeight-(_isIE?4:4))+"px";
		this.obj.className="dhx_combo_edit ty_num";
		this.obj.wrap="soft";this.obj.style.textAlign=this.cell.style.textAlign;
		this.obj.onclick=function(c){(c||event).cancelBubble=true};
		this.obj.onmousedown=function(c){(c||event).cancelBubble=true};
		this.obj.onselectstart=function(c){if(!c){c=event}c.cancelBubble=true};
		this.obj.value=setNumberFormat(this.val);
		this.cell.innerHTML="";
		
		var _div =document.createElement('div');
		_div.className="input_grid";
		_div.appendChild(this.obj);

		this.cell.appendChild(_div);

		$('input,select').keypress(function(event) {return event.keyCode != 13;}); 
		//Object return and set AC
		if(typeof setGridAc == "function") setGridAc(this.grid, this.cell);
		$(this.cell).find('input').focus();
		
		setNumberFmt();

		return true;
    }
	this.detach=function(){
         // sets the new value
        // this.setValue($(this.cell).find('input').val()); 
         if(typeof $(this.cell).find('input').val() != "undefined")
         	this.setCValue($(this.cell).find('input').val().split(",").join(),$(this.cell).find('input').val().split(",").join());  
         
         return true; // compares the new and the old values
    }
    this.setValue=function(val){
        this.setCValue(setNumberFormat(val),val);  
    }
}

eXcell_edtynum.prototype = new eXcell;// nests all other methods from the base class


/**
 * Date Only Read
 * @param cell
 * @returns
 */
function eXcell_datero(cell){  // the eXcell name is defined here
    if (cell){                  // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
    }
    this.edit = function(){}   // read-only cell doesn't have edit method
    this.isDisabled = function(){ return true; }
    /*this.getValue=function(){
        return this.cell.childNodes[0].innerHTML; // gets the value
    }*/
    this.setValue=function(val){
    	var row_id=this.cell.parentNode.idd;
    	this.setCValue(dFormat(val),dFormat(val));  
    	//$(this.cell).prop("title",val);
    	//$(this.cell).text(dFormat(val));
    }
}

eXcell_datero.prototype = new eXcell;// nests all other methods from the base class

/**
 * Date Type Input...Edit
 * @param cell
 * @returns
 */
function eXcell_dedp(cell)//the eXcell name is defined here
{ 
	if (cell){                // the default pattern, just copy it
        this.cell = cell;
        this.grid = this.cell.parentNode.grid;
        //eXcell_ed.call(this); //uses methods of the "ed" type
    }
	this.edit=function(){
		console.log("!!!!!EDIT");
		this.cell.atag=(!this.grid.multiLine)?"INPUT":"TEXTAREA";
		this.val=this.getValue();
		this.obj=document.createElement(this.cell.atag);
		this.obj.setAttribute("autocomplete","off");
		this.obj.style.height=(this.cell.offsetHeight-(_isIE?4:4))+"px";
		this.obj.className="dhx_combo_edit";
		this.obj.wrap="soft";this.obj.style.textAlign=this.cell.style.textAlign;
		this.obj.onclick=function(c){(c||event).cancelBubble=true};
		this.obj.onmousedown=function(c){(c||event).cancelBubble=true};
		this.obj.onselectstart=function(c){if(!c){c=event}c.cancelBubble=true};
		console.log("!!!!"+this.val);
		this.obj.value=this.val;
		this.cell.innerHTML="";
		
		var _span = document.createElement('span');
		var _a =document.createElement('a');
		_a.onclick=function(c){(c||event).cancelBubble=true};
		var _p =document.createElement('p');
		var _div =document.createElement('div');
		_p.className="open_calendar";
		_div.className="input_grid input_carendar";
		_a.appendChild(_span);
		_p.appendChild(_a);
		_div.appendChild(this.obj);
		_div.appendChild(_p);

		this.cell.appendChild(_div);

		//Object return and set AC
		if(typeof setGridAc == "function") setGridAc(this.grid, this.cell);

		setCalendar($(this.cell).find('input'));

		$(this.cell).find('input').focus();
		$('input,select').keypress(function(event) {return event.keyCode != 13;}); 

		$(this.cell).find('input').datepicker("show");
		
		return true;
    }
	this.detach=function(){
         // sets the new value
        // this.setValue($(this.cell).find('input').val()); 
         if(typeof $(this.cell).find('input').val() != "undefined")
         	this.setCValue($(this.cell).find('input').val(),$(this.cell).find('input').val());  
         
         return true; // compares the new and the old values
    } 
    this.setValue=function(val){
    	//console.log(val);
    	//console.log(val+"   "+dFormat(val));
        this.setCValue(val,val);  
    }
}
	
eXcell_dedp.prototype = new eXcell;// nests all other methods from the base class