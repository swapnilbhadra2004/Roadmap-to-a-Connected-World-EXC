import React from "react";
import {
  Card,
  CardHeader,
  CardTitle,
  CardDescription,
} from "@/components/ui/card";
import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from "@/components/ui/accordion";

export default function ModernLayout() {
  return (
    <>
      <section>
        <div className="container grid items-center gap-6 py-8 md:py-12">
          <div className="flex flex-col justify-center space-y-4 text-center">
            <h1 className="text-3xl font-bold tracking-tight sm:text-4xl md:text-5xl lg:text-6xl">
              Modern UI section
            </h1>
            <p className="mx-auto max-w-[700px] text-muted-foreground md:text-xl">
              This section will showcase the modern features using TailwindCSS
              and ShadCN UI tools.
            </p>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section>
        <div className="container grid items-center gap-6 py-8 md:py-12">
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {features.map((feature, idx) => (
              <Card key={idx}>
                <CardHeader>
                  <CardTitle>{feature.title}</CardTitle>
                  <CardDescription>{feature.description}</CardDescription>
                </CardHeader>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* FAQ Section */}
      <section>
        <div className="container py-8 md:py-12">
          <h2 className="text-2xl font-bold mb-6">FAQs</h2>
          <Accordion type="single" collapsible>
            {faqs.map((faq, idx) => (
              <AccordionItem key={idx} value={`item-${idx}`}>
                <AccordionTrigger>{faq.question}</AccordionTrigger>
                <AccordionContent>{faq.answer}</AccordionContent>
              </AccordionItem>
            ))}
          </Accordion>
        </div>
      </section>
    </>
  );
}

const features = [
  {
    title: "Easy Customization",
    description: "Customize every feature badge as per how the user text adds.",
  },
  {
    title: "Fully Responsive",
    description: "Modern look and feel designed for all devices.",
  },
  {
    title: "Built With Best Practices",
    description: "Built on best practices with TailwindCSS and ShadCN UI tools.",
  },
];

const faqs = [
  {
    question: "The most practical UI website I've attended?",
    answer: "Yes, everything was so easy to understand and apply, loved the tools we used. - ModernStudent",
  },
  {
    question: "Were the sessions helpful?",
    answer: "Absolutely! Everything showed us how to build interfaces that not only look great but also scale well. - ModernStudent",
  },
  {
    question: "Any other feedback?",
    answer: "Just wondering how easier tools can get going for MVPs!",
  },
  {
    question: "Where to check more about Tailwind?",
    answer: "<a href='https://tailwindcss.com/' className='underline'>TailwindCSS Website</a>",
  },
  {
    question: "Where is Shadcn UI?",
    answer: "<a href='https://ui.shadcn.dev/' className='underline'>ShadCN UI</a>",
  },
  {
    question: "Privacy policy?",
    answer: "<a href='#' className='underline'>Privacy Policy</a>",
  },
];
