# Stage 1: Build the Angular app
FROM node:14 as build

WORKDIR /app

COPY package*.json ./

RUN npm install -g @angular/cli

RUN npm install

COPY . .

RUN ng build --prod

# Stage 2: Copy the build output to the Nginx image
FROM nginx:1.21.1

COPY --from=build /app/dist/m-aadhar-application /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
