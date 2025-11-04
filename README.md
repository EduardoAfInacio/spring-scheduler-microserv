# spring-scheduler-microserv

Java (Spring Boot) microservice for scheduling and sending notifications. The project demonstrates common Spring development patterns: task scheduling, asynchronous email sending, JPA persistence, and REST endpoints for creating/querying/canceling notifications.

## Overview

- Purpose: provide a simple microservice for scheduling notifications (emails, SMS, etc.) to be executed at future times.
- Implements: notification scheduling, status control, asynchronous email sending (via `EmailService`), and a scheduler responsible for triggering notifications at the correct time.
- Initial loading component (`DataLoader`) and `AsyncConfig` configuration for asynchronous operations.

## Key Features

- REST API for registering, querying, and canceling notifications.
- Scheduling based on `LocalDateTime` (field `dateTime`).
- Entities: `Notification`, `Channel`, `Status`.
- Message sending (email) via `EmailService` using MailHog in development environment (configured in `application.properties`).
- Persistence with Spring Data JPA (MySQL in `docker-compose`).

## Project Structure

- `controller` — REST endpoints (e.g., `NotificationController`) and DTOs (`ScheduleNotificationDTO`).
- `service` — Business logic (`NotificationService`, `EmailService`).
- `repository` — Spring Data JPA repositories (`NotificationRepository`, `ChannelRepository`, `StatusRepository`).
- `entity` — Domain entities (`Notification`, `Channel`, `Status`) and auxiliary enums in `entity/Enum`.
- `scheduler` — Scheduled tasks (`MsTaskScheduler`).
- `config` — Application configurations (`AsyncConfig`, `DataLoader`).
- `resources/application.properties` — Database and email configurations.

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Scheduler
- Jakarta Validation
- MySQL (via Docker Compose)
- MailHog (for email capture in dev)
- Maven (wrapper included)

## Requirements

- JDK 17
- Maven (or use the wrapper `./mvnw` / `mvnw.cmd` on Windows)
- Docker and Docker Compose (for MySQL and MailHog)

## Docker Setup

The project includes a `docker/docker-compose.yaml` with two services:

- `mysql` — MySQL database, port `3306`
- `mailhog` — SMTP server and UI for viewing emails (port `8025`)

To start the services in background:

```powershell
cd docker; docker-compose up -d
```

Note: `application.properties` points to `jdbc:mysql://localhost:3306/scheduler` and uses password `123` for `root` (see file for details). MailHog UI will be available at http://localhost:8025.

## How to Run

1. Start dependencies (MySQL and MailHog):

```powershell
cd docker; docker-compose up -d
```

2. Build the project:

```powershell
./mvnw clean install
```

(On Windows PowerShell use `mvnw.cmd` when needed.)

3. Run the application:

```powershell
./mvnw spring-boot:run
```

4. Run tests:

```powershell
./mvnw test
```

## Main Endpoints

The main controller is `NotificationController` based on `@RequestMapping("/api/v1/notifications")`.

- POST /api/v1/notifications
  - Description: Registers/schedules a new notification.
  - Body (JSON example):

```json
{
  "dateTime": "2025-11-20T15:30:00",
  "destination": "user@example.com",
  "message": "Reminder: meeting tomorrow at 10am",
  "channel": "EMAIL"
}
```

- Notes: The DTO used is `ScheduleNotificationDTO` with validations (`@NotNull`, `@Size`) and has the `toNotification()` method that converts to the entity.
- GET /api/v1/notifications/{notificationId}

  - Description: Returns notification data by ID.
- PATCH /api/v1/notifications/{notificationId}/cancel

  - Description: Cancels a scheduled notification (changes status to canceled).
    ## Development Notes
