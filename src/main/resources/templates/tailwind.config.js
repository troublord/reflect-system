/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}",
    "./src/*.{html,js}",
    "./node_modules/flowbite/**/*.js"
    ],
  theme: {
    extend: {
      padding:{
        '95px': '95px',
      }
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
    require('@tailwindcss/typography'),
    require('flowbite/plugin')
  ],
}
