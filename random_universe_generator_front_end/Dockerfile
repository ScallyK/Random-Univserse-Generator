# Use official Node.js image for building the frontend
FROM node:18-alpine

# Set working directory
WORKDIR /usr/src/app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy all frontend source files
COPY . .

# Build the frontend assets (e.g., React/Vue/Angular)
RUN npm run build

# Install serve to serve the built static files
RUN npm install -g serve

# Expose port 3000
EXPOSE 3000

# Command to serve the built frontend
CMD ["serve", "-s", "build", "-l", "3000"]
