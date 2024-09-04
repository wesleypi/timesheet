# Makefile
compose:
	docker compose up -d

drop_db:
	docker rm -f timesheet_sql
	docker volume rm -f timesheet_mysql_data

stop_db:
	docker stop timesheet_sql
