import pandas as pd
import requests
import json
import time

# Step 1: Read CSV Data
csv_file = './data/first_30_stock_prices_10_companies.csv'
df = pd.read_csv(csv_file)

# Define your API URL
api_url = 'http://app:8080/financial-records'  # Corrected URL

# Retry configuration
max_retries = 10  # Maximum number of retry attempts
wait_seconds = 5  # Number of seconds to wait between retries

# Function to send data for a specific symbol
def send_data(symbol, group):
    # Transform Data into Desired JSON Format
    records = []
    for _, row in group.iterrows():
        record = {
            'closingPrice': row['close_price'],
            'date': row['utc']
        }
        records.append(record)

    # Convert records to JSON
    json_data = json.dumps(records)

    # Define query parameters
    params = {'companyName': symbol}

    # Set headers
    headers = {'Content-Type': 'application/json'}

    # Retry loop
    for attempt in range(max_retries):
        try:
            # Step 4: Send HTTP POST Request to API Endpoint
            response = requests.post(api_url, headers=headers, params=params, data=json_data)

            # Step 5: Handle Response
            if response.status_code == 200:
                print(f'Data for {symbol} successfully sent to API.')
                break  # Exit the retry loop if successful
            else:
                print(f'Failed to send data for {symbol} to API. Status Code: {response.status_code}, Response: {response.text}')

        except requests.exceptions.ConnectionError as e:
            print(f'Connection error occurred: {e}')

        # Wait before retrying
        print(f'Retrying in {wait_seconds} seconds...')
        time.sleep(wait_seconds)
    else:
        print(f'Failed to send data for {symbol} after {max_retries} attempts.')


# Group by 'symbol' to create a structure for each company
for symbol, group in df.groupby('symbol'):
    send_data(symbol, group)
