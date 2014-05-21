Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = '/resources/ext-3.2.1/resources/images/default/s.gif';
	
    var Book = Ext.data.Record.create([
	{name: 'id'},
    {
        name: 'title',
        type: 'string'
    }, {
        name: 'isbn',
        type: 'string'
    }, {
        name: 'author',
        type: 'string'
    }, {
        name: 'publisher',
        type: 'string'
    }]);
    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'book/view.action',
            create : 'book/create.action',
            update: 'book/update.action',
            destroy: 'book/delete.action'
        }
    });
    
    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: 'id',
        root: 'data',
        messageProperty: 'message'
    }, 
    Book);

    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
    });
    
    var store = new Ext.data.Store({
        id: 'user',
        proxy: proxy,
        reader: reader,
        writer: writer,
        autoSave: false
    });

    store.load();
    
    Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
    	Ext.Msg.show({
    		title: 'ERROR',
    		msg: res.message,
    		icon: Ext.MessageBox.ERROR,
    		buttons: Ext.Msg.OK
    	});
    });

    
    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });
    

    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        style:
	    {
	        marginTop: '10px',
	        marginBottom: '10px',
	        marginLeft: '10px'
	    },
        columns: [
            {header: "TITLE",
             width: 170,
             sortable: true,
             dataIndex: 'title',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "ISBN",
             width: 160,
             sortable: true,
             dataIndex: 'isbn',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "AUTHOR",
             width: 170,
             sortable: true,
             dataIndex: 'author',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "PUBLISHER",
                width: 170,
                sortable: true,
                dataIndex: 'publisher',
                editor: {
                   xtype: 'textfield',
                   allowBlank: false
               }}
        ],
        viewConfig:{forcefit:true},
        plugins: [editor],
        title: 'Manage Books',
        height: 300,
        width:705,
		frame:true,
		tbar: [{
            iconCls: 'icon-user-add',
            text: 'Add Book',
            handler: function(){
                var e = new Book({
                    title: 'new book title',
                    isbn: '0000000000',
                    author: 'new author',
                    publisher: 'new publisher'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        },{
            iconCls: 'icon-user-delete',
            text: 'Remove Book',
            handler: function(){
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                    store.remove(r);
                }
            }
        },{
            iconCls: 'icon-user-save',
            text: 'Save All Modifications',
            handler: function(){
                store.save();
            }
        }]
    });

    var gridPanelHolder = new Ext.Panel(
    		{
    		    title: 'Library of Alexandria',
    		    style:
    		    {
    		        marginBottom: '5px'
    		    },
    		    items:[
    		        grid
    		    ],
    		    autoHeight: true,
    		    autoWidth:true,
    		    overflowY: 'auto',
    		    overflowX: 'auto'
    		});

    //render to DIV
    gridPanelHolder.render('crud-grid');
});