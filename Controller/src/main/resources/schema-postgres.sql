CREATE OR REPLACE view history_click AS SELECT count(*) as click , content_id FROM history GROUP BY content_id ORDER BY click DESC;
CREATE OR REPLACE view history_click_category AS select click,cate.id as cate_id,c.* from history_click as h right join content as c on h.content_id = c.id
join category cate on c.category_id = cate.id
 where c.status = 1 ;
CREATE OR REPLACE FUNCTION favorite_category_article_suggestion (cur_user_id Integer )  
RETURNS setof content AS $contents$  
declare  
    contents content%rowtype;
BEGIN  
	RETURN QUERY SELECT * FROM content WHERE category_id in (select distinct category_id from favorite_category where user_id = cur_user_id )
	 order by id desc limit 3;
END;  
$contents$ LANGUAGE plpgsql;  