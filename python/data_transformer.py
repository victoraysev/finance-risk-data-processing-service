import pandas as pd
import random

# Load your data
# Assuming your data is in a CSV file named 'stock_data.csv'
# If the data is already in a DataFrame 'df', skip this step
df = pd.read_csv('data/stock_data.csv')

# Displaying first few rows to understand the data structure
print(df.head())

# Select unique companies
unique_companies = df['symbol'].unique()

# Randomly select 10 companies
random_companies = random.sample(list(unique_companies), 10)

# Filter data for these random companies
filtered_df = df[df['symbol'].isin(random_companies)]

# For each company, get the first 30 entries
final_df = filtered_df.groupby('symbol').head(30)

# Display the final DataFrame
print(final_df)

# Save the final DataFrame to a new CSV file (if needed)
final_df.to_csv('first_30_stock_prices_10_companies.csv', index=False)
