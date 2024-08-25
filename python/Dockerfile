# Use an official Python runtime as a parent image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /data_loader

# Copy the requirements file into the container
COPY requirements.txt .

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Create a directory for data if it doesn't already exist
RUN mkdir -p ./data

# Copy the data CSV file into the container
COPY ./data/first_30_stock_prices_10_companies.csv ./data/

# Copy the Python script into the container
COPY data_loader.py .

# Run the Python script when the container launches
CMD ["python", "data_loader.py"]
