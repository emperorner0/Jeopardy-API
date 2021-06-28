
# Jeopardy Question Search API

A simple REST API that allows a user to upload a CSV of Jeopardy questions.

After using a POST request to upload these CSV you can then search via monetary value of the question, category of the question, round of Jeopardy in which the question was asked, and finally by round and category. All of these are provided through GET requests mapped within the API. Finally the user is able to delete questions by ID by using a DELETE request, and add a question using the body of a POST request and a simple Question object.



## API Reference

#### Upload Questions

```http
  POST /api/CSV/upload
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `file` | `CSV file` | **Required**. A CSV file of Jeopardy questions.|

#### Get all questions

```http
  GET /api/items/questions
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| None      | N/A |Returns a list of all questions uploaded to the JPA repository.

#### Get question by value

```http
  GET /api/items/getQuestionByValue/{value}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| Value      | Integer |Returns a list of all questions worth a given value.

#### Get question by category

```http
  GET /api/items/getQuestionByCategory/{category}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| category      | String |Returns a list of all questions within a given categroy.

#### Get question by round

```http
  GET /api/items/getQuestionByRound/{round}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| Round      | String |Returns a list of all questions asked within a given round.

#### Get question by round and category

```http
  GET /api/items/getQuestionByRoundAndCategory/{round}/{category}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| round / category      | String / String|Returns a list of all questions within a given categroy.

#### Delete question by ID

```http
  DELETE /api/items/deleteById/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| ID      | Long |Deletes the question at the given ID.

#### Add a question

```http
  POST /api/items/addQuestion
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| None     | Question |Adds a question to the database given a JSON mapped to the Question Object.

### Question Object:

```
    private Integer showNumber;
    private String round;
    private String category;
    private Integer value;
    @Column(length = 1000)
    private String question;
    private String answer;
```

  
## Tech Stack

**Client:** Java, Spring, Spring Boot, H2, JPA

  