<%!
from myproject.auth import is_allowed
%>

<!DOCTYPE html>
<html>
<head>
  
  <title>${self.title()}</title>
  ${self.meta()}
  
  <link rel="shortcut icon" href="${request.static_url('myproject:static/favicon.ico')}" />
  <link rel="stylesheet" href="${request.static_url('myproject:static/pyck.css')}" type="text/css" media="screen" charset="utf-8" />
  <link rel="stylesheet" href="${request.static_url('myproject:static/cdsr.css')}" type="text/css" media="screen" charset="utf-8" />
  <link rel="stylesheet" href="http://static.pylonsproject.org/fonts/nobile/stylesheet.css" media="screen" />
  <link rel="stylesheet" href="http://static.pylonsproject.org/fonts/neuton/stylesheet.css" media="screen" />
  <!--[if lte IE 6]>
  <link rel="stylesheet" href="${request.static_url('myproject:static/ie6.css')}" type="text/css" media="screen" charset="utf-8" />
  <![endif]-->
  <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.8.3/dojo/resources/dojo.css" type="text/css" charset="utf-8" />
  <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.8.3/dijit//themes/claro/claro.css" type="text/css" charset="utf-8" />
  <script src="//ajax.googleapis.com/ajax/libs/dojo/1.8.3/dojo/dojo.js" data-dojo-config="isDebug: true, async: true, parseOnLoad: true"></script>
  <script type="text/javascript">
        require(['dojo/parser', 'dojo/domReady'],function(parser,ready){ready(function(){
          parser.parse();
          });});
  </script>

</head>

<body class="${self.body_class()}" ${self.body_attrs()}>
  
  ${self.header()}
  
  ${self.content_wrapper()}
  ${self.footer()}
  
</body>
</html>



<%def name="meta()">
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  <meta name="keywords" content="cellular data security & retrieval system" />
  <meta name="description" content="cellular data security & retrieval system" />
</%def>

<%def name="body_class()">
</%def>
<%def name="body_attrs()">
</%def>
<%def name="header()">
  <div id="top-small">
    <img src="${request.static_url('myproject:static/cdsr-logo-2.png')}" width="360px" height="75px" alt="pyck"/>
    ${self.main_menu()}
  </div>
</%def>
  
<%def name="content_wrapper()">
  <div id="content">
    <div class="flash">
      <% flash_msgs = request.session.pop_flash() %>
      
      %for flash_msg in flash_msgs:
        ${flash_msg}<br />
      %endfor
    </div>
  ${self.body()}
  </div>
</%def>
    
<%def name="main_menu()">

</%def>
<%def name="footer()">
  <div id="footer">
    <div class="footer">&copy; Copyright 2009-2013, IIUI</div>
  </div>
</%def>

