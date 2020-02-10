CREATE OR REPLACE view history_click AS SELECT count(*) as click , content_id FROM history GROUP BY content_id ORDER BY click DESC;
CREATE OR REPLACE view history_click_category AS select click,cate.id as cate_id,c.* from history_click as h right join content as c on h.content_id = c.id
join category cate on c.category_id = cate.id
 where c.status = 1 ;
CREATE  OR REPLACE VIEW history_user_click_category_date as  select h.user_id,h.content_id,cate.id as category_id ,h.timestamp FROM history h
      JOIN content c ON h.content_id = c.id
     JOIN category cate ON c.category_id = cate.id
  WHERE c.status = 1 ;

CREATE OR REPLACE FUNCTION public.top_click_category_article_suggestion(cur_user_id integer)
  RETURNS SETOF history_click_category AS
$BODY$  
declare  
	top_category_id integer;
    contents history_click_category%rowtype;
BEGIN  
	SELECT s.category_id INTO top_category_id FROM (SELECT count(*) as most,v1.category_id from history_user_click_category_date as v1 where user_id = cur_user_id AND timestamp > NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER-7 group by  category_id order by most desc limit 1) as s;
	RETURN QUERY SELECT * FROM history_click_category WHERE category_id = top_category_id;
END;  
$BODY$
  LANGUAGE plpgsql

-- select * from top_click_category_article_suggestion(2) order by click desc NULLS LAST limit 3