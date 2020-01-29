GETTING START 
    /home => user and principal is on development , so we start from /home 

NOTE 
   - When database server down, Project will not be error because of ORM technology .
        so please change database setting in .properties file to local when server down.
    
   - Insert might have problem because ORM require reset DB or insert many time id will increase to correct version
    
PROJECT DETAIL
    port
        :8080
    
    Endpoint
        /               =>  loginpage
        /signup         =>  register
        
    Client    
    /home & /homepage   =>  homepage    (developing)
    /contentByCategory  =>  articlebycategory (developing)
    /detail             =>  specific - article (developing)
    
    Admin               
    /admin              =>  admin-home (developing)
    /admin/category     =>  category   (developing)
    /admin/article      =>  article    (developing)
    /admin/user         =>  user        (developing)
    /admin/feedback     =>  feedback    (developing)
    /admin/question     =>  question (developing)
    /admin/customize    =>  customize (developing)
    
    Other
    /privacy
    /about


LOGIC ERROR OCCUR DURING DEVELOPMENT
    - using ORM generated strategy it may have problem with id generated from insert new
    - crsf has to be disabled during development
    - some error need to be explained so please kindly tell us about the problem (DEVELOPMENT)
    
PLEASE CONTACT ME IF THERE IS ANY PROBLEM
