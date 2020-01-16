/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */
/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */
 
CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_config.html
	config.height = '550px';
	CKEDITOR.config.removePlugins = 'Language,newpage,save,sourcearea,about,flash,iframe,a11yhelp,div,find,forms,pagebreak,templates,scayt,showblocks,smiley,colordialog,magicline,tableselection,tabletools,wsc,Image';
	// config.removeButtons = 'Maximize,Language,Subscript,Superscript,Blockquote,RemoveFormat,CopyFormatting,Underline,JustifyCenter,JustifyBlock,BidiLtr,BidiRtl,Link,Unlink,Anchor,Outdent,Indent,Scayt,Strike';

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons ='Maximize,Language,Subscript,Superscript,Blockquote,RemoveFormat,CopyFormatting,Underline,JustifyCenter,JustifyBlock,BidiLtr,BidiRtl,Link,Unlink,Anchor,Outdent,Indent,Scayt,Strike';
	
	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
	CKEDITOR.plugins.add( 'print', {
		// jscs:disable maximumLineLength
		lang: 'af,ar,az,bg,bn,bs,ca,cs,cy,da,de,de-ch,el,en,en-au,en-ca,en-gb,eo,es,es-mx,et,eu,fa,fi,fo,fr,fr-ca,gl,gu,he,hi,hr,hu,id,is,it,ja,ka,km,ko,ku,lt,lv,mk,mn,ms,nb,nl,no,oc,pl,pt,pt-br,ro,ru,si,sk,sl,sq,sr,sr-latn,sv,th,tr,tt,ug,uk,vi,zh,zh-cn', // %REMOVE_LINE_CORE%
		// jscs:enable maximumLineLength
		icons: 'print,', // %REMOVE_LINE_CORE%
		hidpi: true, // %REMOVE_LINE_CORE%
		init: function( editor ) {
			// Print plugin isn't available in inline mode yet.
			if ( editor.elementMode == CKEDITOR.ELEMENT_MODE_INLINE )
				return;
	
			var pluginName = 'print';
	
			// Register the command.
			editor.addCommand( pluginName, CKEDITOR.plugins.print );
	
			// Register the toolbar button.
			editor.ui.addButton && editor.ui.addButton( 'Print', {
				label: editor.lang.print.toolbar,
				command: pluginName,
				toolbar: 'document,50'
			} );
		}
	} );
	
	CKEDITOR.plugins.print = {
		exec: function( editor ) {
			if ( CKEDITOR.env.gecko ) {
				editor.window.$.print();
			} else {
				editor.document.$.execCommand( 'Print' );
			}
		},
		canUndo: false,
		readOnly: 1,
		modes: { wysiwyg: 1 }
	};
};
