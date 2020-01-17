/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.height = '550px';
    CKEDITOR.config.removePlugins = 'Language,newpage,save,sourcearea,about,flash,iframe,a11yhelp,div,find,forms,pagebreak,templates,scayt,showblocks,smiley,preview,colordialog,magicline,tableselection,tabletools,wsc';
    config.removeButtons = 'Maximize,Language,Subscript,Superscript,Blockquote,RemoveFormat,CopyFormatting,Underline,JustifyCenter,JustifyBlock,BidiLtr,BidiRtl,Link,Unlink,Anchor,Outdent,Indent,Scayt,Strike';
};
