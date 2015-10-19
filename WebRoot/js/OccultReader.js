// Copyright (c) 2010 rock 遊手好閒的石頭成
// Licensed by GNU LGPL.
// 前台條碼讀取操作專用的   --javascript--
OccultReader = new (function() {
    var control = false;
    var onchange_handler = false;
 
    /**
     * id: id of element.
     * args:
     *  style
     *  onchange_handler = function(value)
     */
    this.setControl = function(id, args) {
        control = document.getElementById(id);
 
        if (args && args.onchange_handler)
            onchange_handler = args.onchange_handler;
 
        control.onchange = function() {
            if ( onchange_handler ) {
                onchange_handler(control.value);
            }
            else {
                alert("Input: " + control.value +
                    "\n using args.onchange_handler to set this handler.");
            }
            control.value = ""; //reset data
        };
 
        //keep focus. 保持條碼輸入框焦點
        control.onblur = function() {
            setTimeout("document.getElementById('" + 
                control.id + "').focus();", 100);
        };
 
        if (args && args.style) {
            for (var style in args.style) {
                control.style[style] = args.style[style];
            }
        }
        else {
            with (control.style) {
                color = background = "white";
                border = "0";
                width = "1px";
            }
        }
 
        control.value = "";
        control.focus();
    };
});