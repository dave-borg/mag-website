# Build stage
FROM hugomods/hugo:latest as builder

# Install Node.js and npm
RUN apk add --no-cache nodejs npm

WORKDIR /src

# Copy package files first for better caching
COPY package*.json ./
COPY postcss.config.js ./

# Install PostCSS and dependencies
RUN npm install -D postcss postcss-cli autoprefixer

# Copy the rest of the source
COPY . .

# Build the site
RUN hugo --minify

# Production stage
FROM nginx:alpine

# Install apache2-utils for htpasswd utility
RUN apk add --no-cache apache2-utils

# Copy the nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Create directory for auth files
RUN mkdir -p /etc/nginx/auth

# Copy htpasswd files
COPY .htpasswd-members /etc/nginx/auth/
COPY .htpasswd-company /etc/nginx/auth/
RUN chmod 600 /etc/nginx/auth/.htpasswd-* && \
    chown nginx:nginx /etc/nginx/auth/.htpasswd-*

# Copy the built static files from builder stage
COPY --from=builder /src/public /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]