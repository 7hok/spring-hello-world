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
    /home & /homepage   =>  homepage
    /contentByCategory  =>  articlebycategory
    /detail             =>  specific - article 
    
    Admin               
    /admin              =>  admin-home
    /admin/category     =>  category   
    /admin/article      =>  article    
    /admin/user         =>  user    
    /admin/feedback     =>  feedback
    /admin/question     =>  question
    /admin/customize    =>  customize
    
    Other
    /privacy
    /about


LOGIC ERROR OCCUR DURING DEVELOPMENT
    - using ORM generated strategy it may have problem with id generated from insert new
    - crsf has to be disabled during development
    - some error need to be explained so please kindly tell us about the problem (DEVELOPMENT)
    
PLEASE CONTACT ME IF THERE IS ANY PROBLEM
