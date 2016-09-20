// require ('node-extjs4');

console.log(process.env.NODE_ENV);

// Ext.Loader.setConfig({enabled:true});
//
// Ext.require([
//     'Ext.panel.Panel'
// ]);

Ext.create('Ext.panel.Panel', {
    renderTo: Ext.getBody(),
    width: 400,
    height: 200,
    title: 'Container Panel',
    layout: 'column',
    items: [
        {
            xtype: 'panel',
            title: 'Child Panel 1',
            height: 100,
            columnWidth: 0.5
        },
        {
            xtype: 'panel',
            title: 'Child Panel 2',
            height: 100,
            columnWidth: 0.5
        }
    ]
});