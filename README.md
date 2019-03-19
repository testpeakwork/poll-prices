Poll prices service
=========================

Service polls <a href="https://iextrading.com/developer/" rel="nofollow">IEX Developer Platform</a> on schedule with the indication of certain companies and saves current prices and company info in Google Datastore.

You must define the environment variables for the service at startup:
- `--SYMBOLS=AAPL,FB` - list of companies for getting prices;
- `-- CRON_CURRENT=0 0/1 * * * *` - cron string for schedule (`0 0/1 * * * *` - mean poll every minute);
- `--GOOGLE_APPLICATION_CREDENTIALS=\..\config\sa_credentials.json` - path to sa_credentials.json file with GCloud credentials.

The service gets current prices and saves in database with kinds Company and Stock.