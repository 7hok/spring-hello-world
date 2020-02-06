CREATE OR REPLACE view history_click AS SELECT count(*) as click , content_id FROM history GROUP BY content_id ORDER BY click DESC;
CREATE OR REPLACE view history_click_category AS select click,cate.id as cate_id,c.* from history_click as h right join content as c on h.content_id = c.id
join category cate on c.category_id = cate.id
 where c.status = 1 ;