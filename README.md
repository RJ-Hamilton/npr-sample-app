# npr-sample-app

### Send display a list of NPR headlines
- Preferred method: consume this json via network request
  from [https://legacy.npr.org/assets/api/listening-api-response.json](https://legacy.npr.org/assets/api/listening-api-response.json)
- Alternatively, consume the attached file locally. It contains the same json that representing an
  array of **items**, which represent a list of **headlines**. Consume the json and display a list
  of **headlines**.
- Each **headline** should display the title field from the json with an optional **image**. If the
  image exists for a **headline**, it will be the **square rel** from the **image** array inside the
  **links** object
- Each _headline’s uid_ field is distinct

### Bonus points
Implement clicking a **headline** to open a new view which loads the url into a **WebView**. The url
to load is the first href inside the **web** link array inside the **links** object.
<br>
<br>

## Screenshots
| Light mode  | Dark mode |
| ------------| --------- |
| <img src="https://github.com/user-attachments/assets/3ca4af48-66b3-40e7-a6b9-997894f08203" width=300/> | <img src="https://github.com/user-attachments/assets/640b85fd-7df8-49c3-9964-6a1e7bda8b87" width=300/> |
<br>
<br>

## Demo
https://github.com/user-attachments/assets/56695e41-7960-44ad-ae42-d594abb5c2e0

