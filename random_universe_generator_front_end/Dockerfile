FROM node:24-alpine

WORKDIR /usr/src/app

# Install dependencies
COPY package*.json ./
RUN npm install

# Copy application source
COPY . .

# Build Next.js app
RUN npm run build

EXPOSE 3000

# Start Next.js in production mode
CMD ["npm", "start"]
