CREATE OR REPLACE view history_click AS SELECT count(*) as click , content_id FROM history GROUP BY content_id ORDER BY click DESC;
